;;; DEPRECATED. Don't use node namespace, use js namespace.

(ns kraken-cljs.utils.globals
  (:require [cljs.nodejs :as node])
  (:require-macros [kraken-cljs.utils.nodejs :refer [def-globals]]))

(def-globals ["__dirname" "JSON" "setTimeout"])

