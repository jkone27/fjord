package fjord.ast.pat;

import fjord.ast.NodeVisitor;
import fjord.ast.NodeWithChildren;

public class ListPattern extends NodeWithChildren<Pat> implements Pat {

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public void addPattern(Pat pattern) {
    addChild(pattern);
  }

  public List<Pat> getPatterns() {
    return getChildren();
  }
}
