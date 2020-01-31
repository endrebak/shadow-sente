(ns sente-example.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [sente-example.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[sente-example started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[sente-example has shut down successfully]=-"))
   :middleware wrap-dev})
