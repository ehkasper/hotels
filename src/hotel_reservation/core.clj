(ns hotel-reservation.core)
(require '[clojure.string :as str])

(def lakewood
  { :classification 3
    :weekdays       110
    :weekdaysReward 80
    :weekends       90
    :weekendsReward 80 })

(def bridgewood
  { :classification 4
    :weekdays       160
    :weekdaysReward 110
    :weekends       60
    :weekendsReward 50 })

(def ridgewood
  { :classification 5
    :weekdays       220
    :weekdaysReward 100
    :weekends       150
    :weekendsReward 40 })


(defn is-weekday [day]
  (and (not= day "sat")) (not= day "sun"))

(defn get-day [date]
  (let [day-pattern "\\((\\w+)\\)"]
    (get (re-find (re-pattern day-pattern) date) 1)))

(defn get-dates [dates]
  (str/split dates #", "))

(def str-to-date-formater
  (java.text.SimpleDateFormat. "ddEyyyy"))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

