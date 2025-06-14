import java.util.List;

public class ASTLet implements ASTNode {
    List<Bind> decls;
    ASTNode body;

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        try {
        Environment<ASTType> en = e.beginScope();
        for (Bind b : decls) {
            if (b.getType() != null) {
                ASTType t = b.getType();
                if (t instanceof ASTTId) {
                    t = ((ASTTId) t).get(en);
                }

                en.assoc(b.getId(), t);
                
                ASTType exp = b.getExp().typecheck(en);

                if (!t.isSubTypeOf(exp, en))
                    throw new TypeCheckerError("Illegal type for " + b.getId() + ": expected " + t.toStr() + ", found " + exp.toStr());
                
                
            } else {
                ASTType t = b.getExp().typecheck(en);
                en.assoc(b.getId(), t);
            }
        }
        ASTType bodyType = body.typecheck(en);
        en.endScope();
        return bodyType;
        } catch (InterpreterError err){
            throw new TypeCheckerError(err.getMessage());
        }
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	Environment<IValue> en = e.beginScope();
        for (Bind b : decls) {
            IValue v = b.getExp().eval(en);
            en.assoc(b.getId(), v);
        }
        IValue v = body.eval(en);
        en.endScope();
        return v;
    }

    public ASTLet(List<Bind> decls, ASTNode b) {
	this.decls = decls;
	body = b;
    }

}
