package fjord.ast.pat;

import fjord.ast.Const;
import fjord.ast.NodeVisitor;

public class ConstantPatParam implements Pat {

  private final Const constant;

  public ConstantPatParam(Const constant) {
    this.constant = constant;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Const getConstant() {
    return constant;
  }
}
