#!/usr/bin/env boot

(require '[clojure.string :as string])

(defn parse-line [line] 
  {})

(defn parse-input []
  (->> (slurp "input")
       (string/split-lines)
       (map parse-line)))

(defn problem1 []
       (parse-input))

(defn -main [& args]
  (println (problem1)))
