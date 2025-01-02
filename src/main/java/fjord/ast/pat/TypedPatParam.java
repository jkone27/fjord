package fjord.ast.pat;

import fjord.ast.NodeVisitor;
import fjord.ast.patparam.PatParam;

public class TypedPatParam implements PatParam {

  private final PatParam patParam;
  private final String type;

  public TypedPatParam(PatParam patParam, String type) {
    this.patParam = patParam;
    this.type = type;
  }

  public PatParam getPatParam() {
    return patParam;
  }

  public String getType() {
    return type;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }
}
