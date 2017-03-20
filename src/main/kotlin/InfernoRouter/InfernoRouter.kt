package InfernoRouter

import Inferno.Inferno.Companion.cloneVNode
import Inferno.InfernoComponent
import Inferno.InfernoElement
import createElement
import history.HistoryModule
import kotlin.reflect.KClass

/**
 * Created by ryota on 2017/03/22.
 */

class RouteProps(val path: String?, component: KClass<*>) {
    val component = component.js
}

class IndexRouteProps(component: KClass<*>) {
    val component = component.js
}

data class RouterProps(val history: HistoryModule)
external object InfernoRouter {
    class Router
    class Route
    class IndexRoute
}

data class ConnectRouterParam(val params: dynamic)
data class ConnectRouterContext(val router: Any)
abstract class ConnectRouterComponent<S>(props: ConnectRouterParam, context: ConnectRouterContext) :
        InfernoComponent<ConnectRouterParam, S, ConnectRouterContext>(props, context)

//fun start(): InfernoElement {
//    val hashHistory = History.createHashHistory()
//    return createElement(InfernoRouter.InfernoRouter.Router::class, props = RouterProps(hashHistory), children = arrayOf(
//            createElement(InfernoRouter.InfernoRouter.IndexRoute::class, props = IndexRouteProps(component = ParentComponentSample::class)),
//            createElement(InfernoRouter.InfernoRouter.Route::class, props = RouteProps(component = ParentComponentSample2::class, path = "/huga"))
//    ))
//}

open class RoutingBuilder {
    var routes = emptyArray<InfernoElement>()

    companion object {
        fun init(history: HistoryModule, define: RoutingBuilder.() -> Unit ): InfernoElement {
            val builder = RoutingBuilder()
            builder.define()
            val element = createElement(InfernoRouter.Router::class, props = RouterProps(history), children = builder.routes)
            return element
        }
    }

    data class CurryInfernoElement(val curry: (Array<InfernoElement>?) -> InfernoElement)

    infix fun String.through(component: KClass<*>): CurryInfernoElement {
        val element: (Array<InfernoElement>?) -> InfernoElement = { children ->
            if (children != null) {
                createElement(InfernoRouter.Route::class, props = RouteProps(component = component, path = this), children = children)
            } else {
                createElement(InfernoRouter.Route::class, props = RouteProps(component = component, path = this))
            }
        }
        return CurryInfernoElement(element)
    }

    infix fun String.to(component: KClass<*>): InfernoElement {
        return path(this, component)
    }


    infix fun CurryInfernoElement.nest(define: RoutingBuilder.() -> Unit): InfernoElement {
        val builder = RoutingBuilder()
        builder.define()
        val element = this.curry(builder.routes)
        routes = routes.plus(element)
        return element
    }



    fun path(str: String, component: KClass<*>): InfernoElement {
        val element = createElement(InfernoRouter.Route::class, props = RouteProps(component = component, path = str))
        routes = routes.plus(element)
        return element
    }

    fun path(str: String, component: KClass<*>, define: RoutingBuilder.() -> Unit): InfernoElement {
        val builder = RoutingBuilder()
        builder.define()
        val element = createElement(InfernoRouter.Route::class, props = RouteProps(component = component, path = str), children = builder.routes)
        routes = routes.plus(element)
        return element
    }
}

