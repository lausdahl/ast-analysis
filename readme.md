# Overture AST analysis for VDM-RT


To build the tool use:

```bash
mvn assembly:assembly
```

To use it do:

```bash
java -jar target/ast-analysis-0.0.1-SNAPSHOT-jar-with-dependencies.jar /path/to/folder/with/RT/spectfiles
```

it will print something like:

```
org.overture.ast.expressions.ANotUnaryExp
org.overture.ast.expressions.AIfExp
org.overture.ast.expressions.ASetRangeSetExp
org.overture.ast.expressions.AEqualsBinaryExp
org.overture.ast.definitions.ACpuClassDefinition
org.overture.ast.definitions.APublicAccess
org.overture.ast.lex.LexQuoteToken
org.overture.ast.statements.AErrorStm
org.overture.ast.statements.AForIndexStm
org.overture.ast.definitions.AValueDefinition
org.overture.ast.expressions.ASeqCompSeqExp
org.overture.ast.statements.AMapSeqStateDesignator
```
