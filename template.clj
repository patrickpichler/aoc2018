#!/usr/bin/env boot

(require '[clojure.string :as string])

(defn parse-input []
  (->> (slurp "input")
       (string/split-lines)))

(defn problem1 []
       (parse-input))

(defn -main [& args]
  (println (problem1)))
