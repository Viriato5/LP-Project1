public class ASTLazyList implements ASTNode {
    ASTNode value;
    ASTNode head;


    public ASTLazyList(ASTNode v, ASTNode h) {
        value = v;
        head = h;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        return new VIcons(value, head, e);
    }
   
}
