package dojo

fun Any.assertTrue(condition: Boolean): Unit {
    if (!condition) throw AssertionFailed("Failed asserting false was true")
}
