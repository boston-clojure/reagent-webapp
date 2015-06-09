;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; core.async snippets
;; Ed Sumitra
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; type the following in figwheel clojurescript repl
;; required libraries and macros
(require '[cljs.core.async :as async])
(require-macros '[cljs.core.async.macros :refer [go go-loop]])
(enable-console-print!)

;;; ------------------------
;;; minimal core.async intro
;;; ------------------------
;; create a chan
(def chan-atest (async/chan))
;; put a message into the channel
(go (async/>! chan-atest "hello bosclj 1"))
;; take a message from the channel
(go (println (async/<! chan-atest)))
;; keep printing messages in channel
(go-loop [] (println (async/<! chan-atest)) (recur))

;;; --------------------------------
;;; create a repl event subscription
;;; --------------------------------
(in-ns 'reagent-bosclj.events)
(def chan-repl-logger (async/chan))
(async/sub *event-que* :new-ui-task chan-repl-logger)
(go-loop [] (println "repl log:" (async/<! chan-repl-logger)) (recur))
