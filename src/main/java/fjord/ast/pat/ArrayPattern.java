package fjord.ast.pat;

import fjord.ast.NodeVisitor;
import fjord.ast.NodeWithChildren;

public class ArrayPattern extends NodeWithChildren<Pat> implements Pat {

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public void addPattern(Pat pattern) {
    addChild(pattern);
  }

  public Pat getPattern(int index) {
    return getChild(index);
  }

  public int getPatternCount() {
    return getChildCount();
  }
}
