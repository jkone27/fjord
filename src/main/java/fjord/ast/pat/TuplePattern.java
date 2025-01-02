package fjord.ast.pat;

import fjord.ast.NodeVisitor;
import fjord.ast.NodeWithChildren;

public class TuplePattern extends NodeWithChildren<Pat> implements Pat {

  public TuplePattern(Pat firstElem) {
    super(firstElem);
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public void addElement(Pat element) {
    addChild(element);
  }

  public Pat getElement(int index) {
    return getChild(index);
  }

  public int getElementCount() {
    return getChildren().size();
  }
}
