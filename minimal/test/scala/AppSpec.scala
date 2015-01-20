import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}
import play.api.libs.iteratee.Iteratee
import play.api.mvc.Result
import play.api.test._
import play.api.test.Helpers._
import play.api.Play

class AppSpec extends FlatSpec with Matchers with BeforeAndAfterAll with ScalaFutures {
  implicit lazy val app: FakeApplication = new FakeApplication()

  implicit def resultToString(result: Result) = new String(await(result.body |>>> Iteratee.consume[Array[Byte]]()))

  "The application" should "return index.html on a GET to /" in {
    val routeMaybe = route(FakeRequest(GET, "/"))
    routeMaybe shouldBe 'defined
    whenReady(routeMaybe.get) { result =>
      resultToString(result) should include("<h3>Hello World!")
    }

  }

  override protected def beforeAll() = Play.start(app)

  override protected def afterAll() = Play.stop()
}

// vim: set ts=2 sw=2 et sts=2:
