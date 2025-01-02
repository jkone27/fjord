package fjord.ast.pat;

import fjord.ast.NodeVisitor;
import fjord.ast.patparam.PatParam;

import java.util.List;

public class TuplePatParam implements PatParam {

  private final List<PatParam> elements;

  public TuplePatParam(List<PatParam> elements) {
    this.elements = elements;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public List<PatParam> getElements() {
    return elements;
  }
}