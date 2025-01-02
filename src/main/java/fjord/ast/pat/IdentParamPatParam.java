package fjord.ast.pat;

import fjord.ast.NodeVisitor;
import fjord.ast.patparam.PatParam;

public class IdentParamPatParam implements Pat {

  private final String ident;

  private final PatParam patParam;

  public IdentParamPatParam(String ident, PatParam patParam) {
    this.ident = ident;
    this.patParam = patParam;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public String getIdent() {
    return ident;
  }

  public PatParam getPatParam() {
    return patParam;
  }

}
