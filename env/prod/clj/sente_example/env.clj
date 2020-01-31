(ns sente-example.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[sente-example started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[sente-example has shut down successfully]=-"))
   :middleware identity})
