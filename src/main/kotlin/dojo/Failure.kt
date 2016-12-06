package dojo

data class Failure(val testCase: () -> Unit, val e: AssertionFailed)