(ns kraken-cljs.core
  (:require [cljs.nodejs :as node]
            [kraken-cljs.utils.core :as utils :refer [p]]))

(enable-console-print!)
(node/enable-util-print!)

(def express (node/require "express"))

(defn say-hello! [req res]
  (-> res (.send "Hello World!")))

(defn -main []
  (let [app (express)]
    (-> app (.get "/" say-hello!))
    (-> app (.listen 3000 (fn [] (p "Server started on port 3000"))))))

(set! *main-cli-fn* -main)

