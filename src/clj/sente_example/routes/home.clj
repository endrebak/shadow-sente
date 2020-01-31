(ns sente-example.routes.home
  (:require
    [sente-example.layout :as layout]
    [taoensso.sente :as sente]
    [clojure.java.io :as io]
    [sente-example.middleware :as middleware]
    [immutant.web :as immutant]
    [taoensso.sente.server-adapters.immutant      :refer (get-sch-adapter)]

    [ring.util.http-response :as response]))

(defn home-page [request]
  (layout/render request "home.html"))

;;; Add this: --->
(let [{:keys [ch-recv send-fn connected-uids
              ajax-post-fn ajax-get-or-ws-handshake-fn]}
      (sente/make-channel-socket! (get-sch-adapter) {})]

  (def ring-ajax-post                ajax-post-fn)
  (def ring-ajax-get-or-ws-handshake ajax-get-or-ws-handshake-fn)
  (def ch-chsk                       ch-recv) ; ChannelSocket's receive channel
  (def chsk-send!                    send-fn) ; ChannelSocket's send API fn
  (def connected-uids                connected-uids) ; Watchable, read-only atom
  )

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/chsk" {:get ring-ajax-get-or-ws-handshake
             :post ring-ajax-post}]
   ["/docs" {:get (fn [_]
                    (-> (response/ok (-> "docs/docs.md" io/resource slurp))
                        (response/header "Content-Type" "text/plain; charset=utf-8")))}]])
