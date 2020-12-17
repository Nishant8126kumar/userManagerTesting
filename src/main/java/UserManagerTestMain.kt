import component.DaggerTestComponent
import component.TestComponent


fun main() {
    val testComponent: TestComponent = DaggerTestComponent.builder().build()
    testComponent.server()
}