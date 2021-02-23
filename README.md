# react-native-rn-thub-text-recognition

Text recognition from images

## Installation

```sh
npm install react-native-rn-thub-text-recognition
```

## Usage

```js
import RnThubTextRecognition from 'react-native-rn-thub-text-recognition';

RnThubTextRecognition.textDetection(
  imagePath,
  (data) => {
    console.log(data);
  },
  (errorMessage) => {
    console.log(errorMessage);
  }
);
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
