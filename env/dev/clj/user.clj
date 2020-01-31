(ns user
  "Userspace functions you can run by default in your local REPL."
  (:require
    [sente-example.config :refer [env]]
    [clojure.spec.alpha :as s]
    [expound.alpha :as expound]
    [mount.core :as mount]
    [sente-example.core :refer [start-app]]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(add-tap (bound-fn* clojure.pprint/pprint))

(defn start 
  "Starts application.
  You'll usually want to run this on startup."
  []
  (mount/start-without #'sente-example.core/repl-server))

(defn stop 
  "Stops application."
  []
  (mount/stop-except #'sente-example.core/repl-server))

(defn restart 
  "Restarts application."
  []
  (stop)
  (start))


