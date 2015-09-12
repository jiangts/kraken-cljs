(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'kraken-cljs.core
   :output-to "out/kraken_cljs.js"
   :output-dir "out"})
