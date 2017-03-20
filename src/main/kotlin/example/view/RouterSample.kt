package example.view

import Inferno.InfernoElement
import InfernoRouter.IndexRouteProps
import InfernoRouter.RouteProps
import InfernoRouter.RouterProps
import InfernoRouter.RoutingBuilder
import createElement
import history.History

/**
 * Created by ryota on 2017/03/22.
 */

fun start(): InfernoElement {
    val hashHistory = History.createHashHistory()
    return createElement(InfernoRouter.InfernoRouter.Router::class, props = RouterProps(hashHistory), children = arrayOf(
            createElement(InfernoRouter.InfernoRouter.IndexRoute::class, props = IndexRouteProps(component = ParentComponentSample::class)),
            createElement(InfernoRouter.InfernoRouter.Route::class, props = RouteProps(component = ParentComponentSample2::class, path = "/huga"))
    ))
}

fun sample(): InfernoElement  {
    val hashHistory = History.createHashHistory()

    return RoutingBuilder.init(hashHistory) {
        "/" to ParentComponentSample2::class
        "/huga" through ParentComponentSample::class nest {
            "/hoge" to ParentComponentSample::class
        }
    }
}
