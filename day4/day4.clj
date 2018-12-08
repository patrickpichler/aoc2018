#!/usr/bin/env boot

(require '[clojure.string :as string])
(import java.time.format.DateTimeFormatter)
(import java.time.LocalDateTime)

(def date-time-formatter (. DateTimeFormatter ofPattern "yyyy-MM-dd HH:mm"))

(defn parse-date [date-string] 
  (. LocalDateTime parse date-string date-time-formatter))

(defn split-line [line]
 (rest (re-matches #"\[(.*)\] (?:Guard #([0-9]*) )?+(.*)" line)))

(defn parse-line [line] 
  (let [[date-time guard-id action] (split-line line)]
    {:date (parse-date date-time)
     :guard-id guard-id
     :action action}))

(defn parse-input []
  (->> (slurp "input")
       (string/split-lines)
       (map parse-line)
       (sort-by :date)))

(defn problem1 []
       (->> (parse-input)
            (partition-by (comp nil? :guard-id))
            (partition 2)
            (map (fn [[f] [s]] (list (:guard-id f) s)))))

(defn -main [& args]
  (println (problem1)))
