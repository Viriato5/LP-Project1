public class ASTFn implements ASTNode {
    String id;
    ASTNode body;

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        return new VClosure(id, body, e);
    }

    public ASTFn(String id, ASTNode b) {
        this.id = id;
        body = b;
    }

    public ASTNode getBody() {
        return body;
    }

}
