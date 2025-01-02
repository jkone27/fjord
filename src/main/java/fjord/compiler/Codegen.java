package fjord.compiler;

import fjord.ast.expr.*;
import fjord.runtime.*;
import fjord.ast.*;
import fjord.*;

import static me.qmx.jitescript.CodeBlock.newCodeBlock;
import static me.qmx.jitescript.JiteClass.*;
import static me.qmx.jitescript.util.CodegenUtils.p;
import static me.qmx.jitescript.util.CodegenUtils.sig;
import me.qmx.jitescript.*;

public class Codegen extends DefaultNodeVisitor {

  private JiteClass jiteClass;
  private CodeBlock code;
  private String klass;

  public Codegen(String klass) {
    this.klass = klass;
    this.jiteClass = new JiteClass(klass, new String[] { p(Value.class) }) {{
      defineDefaultConstructor();
    }};
  }

  @Override public void visit(final ConstantExpression constant) {
    Const cons = constant.getCons();
    code.ldc(cons.parseValue());
  }

  @Override public void visitBefore(final ApplicationExpression expr) {
  }

  @Override public void visitAfter(final ApplicationExpression expr) {
    if (expr.op() == Operators.PLUS) {
      code.iadd();
    } else if (expr.op() == Operators.MINUS) {
      code.isub();
    } else {
      throw new IllegalStateException("operator " + expr.op() + " not supported.");
    }
  }

  @Override public void visitBefore(final ValueDefn defn) {
    code = newCodeBlock();
    code.newobj(p(defn.getTypeClass().getRefType()));
    code.dup();
  }

  @Override public void visitAfter(final ValueDefn defn) {
    code.invokespecial(p(defn.getTypeClass().getRefType()), "<init>", sig(void.class, defn.getTypeClass().getPrimType()));
    code.areturn();
    jiteClass.defineMethod(defn.pattern(), ACC_PUBLIC | ACC_STATIC, sig(Object.class), code);
    jiteClass.defineMethod("eval", ACC_PUBLIC, sig(Object.class), new CodeBlock() {{
      invokedynamic(defn.pattern(), sig(Object.class), Bootstrap.HANDLE, klass);
      areturn();
    }});
    JiteClassLoader.INSTANCE.define(jiteClass);
  }

  @Override public void visit(final MatchExpression matchExpr) {
    code = newCodeBlock();
    matchExpr.getMatchExpr().accept(this);
    for (Rule rule : matchExpr.getRules()) {
      rule.accept(this);
    }
  }

  @Override public void visit(final Rule rule) {
    rule.getPattern().accept(this);
    rule.getRuleExpr().accept(this);
  }

  @Override public void visit(final ConstantPattern pattern) {
    code.ldc(pattern.getConstant().parseValue());
  }

  @Override public void visit(final NamedPattern pattern) {
    code.ldc(pattern.getIdent());
  }

  @Override public void visit(final WildcardPattern pattern) {
    code.aconst_null();
  }
}
