(ns hotel-reservation.core-test
  (:require [clojure.test :refer :all]
            [hotel-reservation.core :refer :all]))

(deftest hotel-structures
  (testing "Lakewood"
           (lakewood { :classification 3 :weekdays 110 :weekdaysReward 80 :weekends 90 :weekendsReward 80 })
           (bridgewood { :classification 4 :weekdays 160 :weekdaysReward 110 :weekends 60 :weekendsReward 50 })
           (ridgewood { :classification 5 :weekdays 220 :weekdaysReward 100 :weekends 150 :weekendsReward 40 })
   ))

(deftest test-dates
  (testing "testing date formats"
    (is (= (java.time.LocalDateTime/of 2019 11 27 0 0) (java.time.LocalDateTime/of 2019 11 27 0 1)))))
