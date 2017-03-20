/**
 * Created by ryota on 2017/03/22.
 */
const Inferno = require("inferno");
window["Inferno"] = Inferno;
const InfernoComponent = require("inferno-component");
window["InfernoComponent"] = InfernoComponent;
window["Inferno"].Component = InfernoComponent;
const History = require("history");
window["History"] = History;
const InfernoCreateClass = require("inferno-create-class");

window["infernoCreateClass"] = InfernoCreateClass;
window["infernoTextElement"] = String;
window["infernoCreateElement"] = require("inferno-create-element");
window["InfernoRouter"] = require("inferno-router");
