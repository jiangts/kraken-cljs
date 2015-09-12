(defproject kraken-cljs "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.122" :classifier "aot"
                  :exclusion [org.clojure/data.json]]
                 [org.clojure/data.json "0.2.6" :classifier "aot"]]
  :jvm-opts ^:replace ["-Xmx1g" "-server"]
  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-npm "0.6.1"]]
  :profiles  {:dev {:dependencies [[com.cemerick/piggieback "0.2.1"]
                                   [org.clojure/tools.nrepl "0.2.10"]]
                    :repl-options {:nrepl-middleware  [cemerick.piggieback/wrap-cljs-repl]}}}
  :npm {:dependencies [[source-map-support "0.3.2"]
                       [express "4.11.1"]]}
  :source-paths ["src" "target/classes"]
  :clean-targets ["out" "release"]
  :target-path "target"
  :cljsbuild {:builds [{:id "server"
                        :source-paths ["src" "target/classes"]
                        :compiler {:main kraken-cljs.core
                                   :output-to "out/server.js"
                                   :output-dir "out"
                                   :optimizations :none
                                   :target :nodejs
                                   :cache-analysis true
                                   :source-map true}}]})
