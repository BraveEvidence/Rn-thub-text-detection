import { NativeModules } from 'react-native';

type RnThubTextRecognitionType = {
  multiply(a: number, b: number): Promise<number>;
};

const { RnThubTextRecognition } = NativeModules;

export default RnThubTextRecognition as RnThubTextRecognitionType;
