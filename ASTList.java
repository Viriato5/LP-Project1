public class ASTList implements ASTNode {
    ASTNode value;
    ASTNode head;

    public ASTList(ASTNode v, ASTNode h) {
        value = v;
        head = h;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        return new Vcons(value.eval(e), head.eval(e));
    }
   
}
