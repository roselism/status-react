(ns status-im.ui.components.webview-bridge
  (:require [reagent.core :as reagent]))

(def webview-bridge1         (js/require "react-native-webview-bridge"))

(def webview-bridge-class
  (reagent/adapt-react-class webview-bridge1))

(defn webview-bridge [opts]
  [webview-bridge-class opts])
