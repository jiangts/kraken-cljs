(ns kraken-cljs.core
  (:require [cljs.nodejs :as node]
            [kraken-cljs.utils.core :as utils :refer [p] :include-macros true])
  (:require-macros [kraken-cljs.utils.nodejs :refer [require*]]))

(enable-console-print!)
(node/enable-util-print!)

(require* "express")

(defn say-hello! [req res]
  (-> res (.send "Hello World!")))

(defn -main []
  (let [app (express)]
    (-> app (.get "/" say-hello!))
    (-> app (.listen 3000 (fn [] (p "Server started on port 3000"))))))

(set! *main-cli-fn* -main)

