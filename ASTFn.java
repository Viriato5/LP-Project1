public class ASTFn implements ASTNode {
    String id;
    ASTType argType;
    ASTNode body;

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        try {
            if (argType instanceof ASTTId) {
                argType = ((ASTTId) argType).get(e);
            }
            Environment<ASTType> newEnv = e.beginScope();
            newEnv.assoc(id, argType);
            ASTType bodyType = body.typecheck(newEnv);
            
            ASTTArrow ret = new ASTTArrow(argType, bodyType);
            newEnv.endScope();
            return ret;
            // System.out.println("Function: " + _identifier + " receives: " + argType.toStr() + " e devolve " + bodyType.toStr());
            //return rightAssociate(argType, bodyType);
        } catch (InterpreterError err) {
            throw new TypeCheckerError(err.getMessage());
        }
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        return new VClosure(id, body, e);
    }

    public ASTFn(String id, ASTType type, ASTNode b) {
        this.id = id;
        body = b;
        argType = type;
    }

    public ASTNode getBody() {
        return body;
    }

    public void setBody(ASTNode body) {
        this.body = body;
    }
}
