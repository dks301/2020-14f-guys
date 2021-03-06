import React from "react";
import { Alert, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import Axios from "axios";
import { SERVER_BASE_URL } from "../../../utils/constants";
import { useRecoilValue } from "recoil";
import { userTokenState } from "../../atoms";

const UnregisterButtonContainer = ({ navigation }) => {
  const token = useRecoilValue(userTokenState);

  const requestUnregister = () => {
    Axios.delete(`${SERVER_BASE_URL}/api/members`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    navigation.navigate("Home");
  };
  const createTwoButtonAlert = () =>
    Alert.alert(
      "Unregister",
      "Why so serious",
      [
        {
          text: "Nope",
          onPress: () => console.log("someone tried to unregister"),
          style: "cancel",
        },
        { text: "Yes", onPress: requestUnregister },
      ],
      { cancelable: false }
    );

  return (
    <View style={styles.unregisterBtnContainer}>
      <TouchableOpacity style={styles.button} onPress={createTwoButtonAlert}>
        <Text style={styles.buttonText}>Unregister</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  unregisterBtnContainer: {
    flex: 1,
    alignItems: "center",
    paddingBottom: 50,
  },
  button: {
    marginTop: 50,
    borderWidth: 1,
    width: 150,
    height: 50,
    borderColor: "#c8d1d3",
    borderRadius: 100,
    alignItems: "center",
    justifyContent: "center",
  },
  buttonText: {
    color: "#334856",
    fontSize: 14,
  },
});

export default UnregisterButtonContainer;
