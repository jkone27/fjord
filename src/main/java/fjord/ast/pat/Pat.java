package fjord.ast.pat;

import fjord.ast.Node;

public interface Pat extends Node {

  void accept(NodeVisitor visitor);

}
