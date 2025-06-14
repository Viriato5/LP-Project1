import java.util.List;

public class ASTMatchUnion implements ASTNode {
        ASTNode variable;
        List<Bind> struct;

        public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
                ASTType var = variable.typecheck(e);
                if (var instanceof ASTTId){
                        var = ((ASTTId) var).get(e);
                }
                if (!(var instanceof ASTTUnion)) {
                        throw new TypeCheckerError("Match unit expects a union type, found: " + var.toStr());
                }
                ASTTUnion unionType = (ASTTUnion) var;
                boolean hasField = false;
                for (Bind b : struct) {
                        if (unionType.isInUnion(b.getId())) {
                                hasField = true;
                                break;
                        }
                }

                if (!hasField) {
                        throw new TypeCheckerError("Match unit does not match any field in union: " + unionType.toStr());
                }

                try {
                        ASTType outT = null;
                        for (Bind b : struct) {
                                if (!unionType.isInUnion(b.getId())) {
                                        continue;
                                }
                                Environment<ASTType> newEnv = e.beginScope();
                                newEnv.assoc(((ASTTId)b.getType()).toStr(), unionType.getFieldType(b.getId()));
                                ASTType newoutT = b.getExp().typecheck(newEnv);
                                newEnv.endScope();
                                if (outT != null && !(outT.isSubTypeOf(newoutT, e) || newoutT.isSubTypeOf(outT, e))) {
                                        throw new TypeCheckerError("Type mismatch in match unit: " + outT.toStr() + " and " + newoutT.toStr());
                                }
                                outT = newoutT;
                        }
                        return outT;
                } catch (InterpreterError err) {
                        throw new TypeCheckerError(err.getMessage());
                }
        }

        

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue maybeUnion = variable.eval(e);
                if (!(maybeUnion instanceof VUnion)) {
                        throw new InterpreterError("Input of match is not a union: " + maybeUnion.toStr());
                }

                VUnion unionValue = (VUnion) maybeUnion;
                for (Bind b : struct) {
                        if (unionValue.getNameOfValue().equals(b.getId())) {
                                Environment<IValue> newEnv = e.beginScope();
                                newEnv.assoc(((ASTTId)b.getType()).toStr(), unionValue.get());
                                IValue result = b.getExp().eval(newEnv);
                                newEnv.endScope();
                                return result;
                        }
                }
        
                throw new InterpreterError("Match unit does not match any field in union: " + maybeUnion.toStr());
        }

        public ASTMatchUnion(ASTNode variable, List<Bind> struct) {
                this.variable = variable;
                this.struct = struct;
        }

}
