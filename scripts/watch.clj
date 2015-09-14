(require '[cljs.build.api :as b])

(comment (b/watch "src"
  {:main 'kraken-cljs.core
   :output-to "out/kraken_cljs.js"
   :output-dir "out"}))

;;; found :watch-fn option by surfing the source!
;;; https://github.com/clojure/clojurescript/blob/master/src/main/clojure/cljs/build/api.clj
;;; led me to 
;;; https://github.com/clojure/clojurescript/blob/master/src/main/clojure/cljs/closure.clj

(b/watch "src"
         {:main 'kraken-cljs.core
          :output-to "out/server.js"
          :output-dir "out"
          :optimizations :none
          :target :nodejs
          :cache-analysis true
          :source-map true
          :watch-fn (fn [] (println "\n"))})

