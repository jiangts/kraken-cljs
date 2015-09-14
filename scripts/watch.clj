(require '[cljs.build.api :as b])

(comment (b/watch "src"
  {:main 'kraken-cljs.core
   :output-to "out/kraken_cljs.js"
   :output-dir "out"}))

(b/watch "src"
         {:main 'kraken-cljs.core
          :output-to "out/server.js"
          :output-dir "out"
          :optimizations :none
          :target :nodejs
          :cache-analysis true
          :source-map true
          :watch-fn (fn [] (println "\n"))})
