(ns status-im.ui.components.camera
  (:require [goog.object :as object]
            [reagent.core :as reagent]
            [clojure.walk :as walk]))

(def camera1 (js/require "react-native-camera"))

(def default-camera (.-default camera1))

(defn constants [t]
  (-> camera1
      (object/get "constants")
      (object/get t)
      (js->clj)
      (walk/keywordize-keys)))

(def aspects (constants "Aspect"))
(def capture-targets (constants "CaptureTarget"))
(def torch-modes (constants "TorchMode"))

(defn set-torch [state]
  (set! (.-torchMode default-camera) (get torch-modes state)))

(defn request-access-ios [then else]
  (-> (.checkVideoAuthorizationStatus default-camera)
      (.then (fn [allowed?] (if allowed? (then) (else))))
      (.catch else)))

(defn camera [props]
  (reagent/create-element default-camera (clj->js (merge {:inverted true} props))))

(defn get-qr-code-data [code]
  (.-data code))
