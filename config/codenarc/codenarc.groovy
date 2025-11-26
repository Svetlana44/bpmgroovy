ruleset {
    // Базовые правила форматирования
    description 'CodeNarc конфигурация для проекта bpmgroovy'
    
    // Исключения для тестовых файлов
    exclude '**/test/**'
    exclude '**/Test*.groovy'
    exclude '**/*Test.groovy'
    exclude '**/*Tests.groovy'
    
    // Размер класса
    ClassSize {
        maxLines = 1000
        maxLinesPerMethod = 100
    }
    
    // Размер метода
    MethodSize {
        maxLines = 50
        maxParameters = 10
    }
    
    // Именование
    ClassName {
        regex = /^[A-Z][a-zA-Z0-9]*$/
    }
    
    MethodName {
        regex = /^[a-z][a-zA-Z0-9]*$/
    }
    
    VariableName {
        regex = /^[a-z][a-zA-Z0-9]*$/
    }
    
    // Пустые блоки
    EmptyCatchBlock
    EmptyIfStatement
    EmptyElseBlock
    EmptyFinallyBlock
    EmptyForStatement
    EmptyWhileStatement
    
    // Неиспользуемый код
    UnusedPrivateField
    UnusedPrivateMethod
    UnusedPrivateMethodParameter
    
    // Сложность
    CyclomaticComplexity {
        maxMethodComplexity = 20
    }
    
    // Дублирование кода
    DuplicateStringLiteral {
        minLength = 5
        maxOccurrences = 3
    }
    
    // Исключения
    CatchException
    CatchError
    CatchThrowable
    
    // Null проверки
    ReturnNullFromCatchBlock
    
    // Println
    Println
    
    // TODO комментарии
    // TODO: можно включить, если нужно отслеживать TODO
    // TodoComment
    
    // GString в ключах
    GStringAsMapKey
    
    // Небезопасные операции
    FileCreateTempFile
    RandomDoubleCoercedToZero
    
    // Naming
    FactoryMethodName {
        regex = /^[a-z][a-zA-Z0-9]*$/
    }
    
    // Spacing
    SpaceAfterClosingBrace
    SpaceAfterOpeningBrace
    SpaceAfterComma
    SpaceAfterFor
    SpaceAfterIf
    SpaceAfterWhile
    SpaceAfterSwitch
    SpaceAfterCatch
    SpaceAfterSynchronized
    
    // Formatting
    BracesForClass
    BracesForForLoop
    BracesForIfElse
    BracesForMethod
    BracesForTryCatchFinally
    BracesForWhile
    
    // Импорты
    DuplicateImport
    ImportFromSamePackage
    MisorderedStaticImports
    
    // Прочее
    AssignmentInConditional
    BooleanMethodReturnsNull
    ConfusingTernary
    DeadCode
    DoubleNegative
    EmptyMethod
    EmptyStaticInitializer
    ExplicitCallToAndMethod
    ExplicitCallToCompareToMethod
    ExplicitCallToDivMethod
    ExplicitCallToEqualsMethod
    ExplicitCallToGetAtMethod
    ExplicitCallToLeftShiftMethod
    ExplicitCallToMinusMethod
    ExplicitCallToModMethod
    ExplicitCallToMultiplyMethod
    ExplicitCallToOrMethod
    ExplicitCallToPlusMethod
    ExplicitCallToPowerMethod
    ExplicitCallToPutAtMethod
    ExplicitCallToRightShiftMethod
    ExplicitCallToXorMethod
    ForLoopShouldBeWhileLoop
    IfStatementCouldBeTernary
    InvertedIfElse
    LongLiteralWithLowerCaseL
    MissingNewlineAtEndOfFile
    NoDef
    ParameterReassignment
    PublicInstanceField
    ReturnFromFinallyBlock
    SerialVersionUID
    SerializableClassMustDefineSerialVersionUID
    StatelessClass
    TernaryCouldBeElvis
    ThreadLocalNotStaticFinal
    UnnecessaryBigDecimalInstantiation
    UnnecessaryBigIntegerInstantiation
    UnnecessaryBooleanExpression
    UnnecessaryCallForLastElement
    UnnecessaryCallToSubstring
    UnnecessaryCast
    UnnecessaryCollectCall
    UnnecessaryCollectionCall
    UnnecessaryContinue
    UnnecessaryDefInMethodDeclaration
    UnnecessaryDefInVariableDeclaration
    UnnecessaryDotClass
    UnnecessaryDoubleInstantiation
    UnnecessaryElseStatement
    UnnecessaryFinalOnLocalVariable
    UnnecessaryFinalOnPrivateMethod
    UnnecessaryGString
    UnnecessaryIfStatement
    UnnecessaryInstantiationToGetClass
    UnnecessaryLongInstantiation
    UnnecessaryNullCheck
    UnnecessaryNullCheckBeforeInstanceOf
    UnnecessaryObjectReferences
    UnnecessaryOverridingMethod
    UnnecessaryPackageReference
    UnnecessaryParenthesesForMethodCallWithClosure
    UnnecessaryPublicModifier
    UnnecessaryReturnKeyword
    UnnecessarySafeNavigationOperator
    UnnecessarySelfAssignment
    UnnecessarySemicolon
    UnnecessaryStringInstantiation
    UnnecessarySubstring
    UnnecessaryTernaryExpression
    UnnecessaryTransientModifier
    UnusedImport
    UnusedObject
    UseAssertEqualsInsteadOfAssertTrue
    UseAssertFalseInsteadOfNegation
    UseAssertNullInsteadOfAssertEquals
    UseAssertSameInsteadOfAssertEquals
    UseAssertTrueInsteadOfAssertEquals
    UseCollectMany
    UseCollectNested
    UseGStringExpression
    UseIndexOf
    UseKeySet
    UseOfNonFinalSubclass
    UseOfNotifyMethod
    UseOfNotifyAllMethod
    UseOfWaitMethod
    UseReplaceAll
    UseReplaceFirst
    UseStringEquals
    UseValueOf
}

