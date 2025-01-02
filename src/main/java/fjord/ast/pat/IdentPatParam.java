package fjord.ast.pat;

import fjord.ast.NodeVisitor;
import fjord.ast.patparam.PatParam;

public class IdentPatParam implements PatParam {

  private final String ident;

  public IdentPatParam(String ident) {
    this.ident = ident;
  }

  public String getIdent() {
    return ident;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

}
