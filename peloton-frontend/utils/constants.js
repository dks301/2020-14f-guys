import { animated } from "react-spring";
import { Image, Text, View } from "react-native";

export const COLOR = {
  GREEN: "#B0D4D9",
  BLUE: "#B0C0D9",
  PURPLE: "#B5B0D9",
  LAVENDER: "#CAB0D9",
  PINK: "#D0B0D4",
  INDIAN_PINK: "#D9B0C0",
  WHITE: "#FFFFFF",
  GRAY1: "rgb(142,142,147)",
  GRAY2: "rgb(174,174,178)",
  GRAY3: "rgb(199,199,204)",
  GRAY4: "rgb(209,209,214)",
  GRAY5: "rgb(229,229,234)",
  GRAY6: "rgb(33,33,34)",
  LOGIN_BLUE: "#21365d",
};

export const SAMPLE_IMAGES = [
  {
    title: "레이스1",
    src:
      "https://i.pinimg.com/736x/fd/e7/6d/fde76d74009056da95e35abec597f22e.jpg",
    text:
      "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요",
  },
  {
    title: "레이스2",
    src:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQqH2LDKlEd-C6ahSmsVLD0LLAnTc4oy7NAtw&usqp=CAU",
    text: "레이스2",
  },
  {
    title: "레이스3",
    src: "https://pbs.twimg.com/media/EADIWaaU4AI5lK_.jpg:small",
    text: "레이스3",
  },
  {
    title: "레이스4",
    src: "https://t1.daumcdn.net/cfile/tistory/2563273358A2A65114",
    text: "레이스4",
  },
];

export const IMAGE_URL =
  "https://t1.daumcdn.net/cfile/tistory/2657B9505809B4B634";

export const MOCK_DATA = [
  {
    url: "https://i.ytimg.com/vi/CNpa9nCy1_0/maxresdefault.jpg",
  },
  {
    url:
      "https://femiwiki-uploaded-files-thumb.s3.amazonaws.com/0/0c/%ED%81%AC%EB%A6%BC%ED%9E%88%EC%96%B4%EB%A1%9C%EC%A6%88_%EC%BD%94%EC%BD%94.jpeg/600px-%ED%81%AC%EB%A6%BC%ED%9E%88%EC%96%B4%EB%A1%9C%EC%A6%88_%EC%BD%94%EC%BD%94.jpeg",
  },
  {
    url:
      "https://lh3.googleusercontent.com/proxy/e3uLvafFwENFd9nLKJgQ3SZjcfvZR18ovofF5VYOn7CFJIHiXN4TkJAd7i8fD3LjddvqoO_b83SwWuLEm38aF3T_ga1IGlGjfPCOG-wJbTlstQMayRJUeTWr5FzP1dUrjqei3tqov2Fo2-jfkNIa6oZ8TQVgv_paZbd9yHyWwrMFp0PSUJj2yRDRuDzK6Fs3J5dpgCmRxn73hyINosldQPPovuWWyWF_hH60ORSISMheIUfHUKGITHLJBB0dwJfswTRD_7n9p4HHxjQjbmc",
  },
  {
    url:
      "data:image/jpeg;base64,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",
  },
  {
    url:
      "https://dispatch.cdnser.be/wp-content/uploads/2017/08/20170830171358_img_9459.png",
  },
  {
    url:
      "data:image/jpeg;base64,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",
  },
];

export const SAMPLE_SUBTITLE = [
  "당신만의 레이스를 달려보세요!",
  "즐거운 하루 되세요!",
  "오늘 날씨가 참 좋네요! 😝",
];

export const CATEGORY = [
  {
    title: "모임",
    subtitle: "Ice Breaking",
    category: "TIME",
    src: "https://img.sbs.co.kr/newimg/news/20181019/201240131_1280.jpg",
  },
  {
    title: "학습",
    subtitle: "Learning",
    category: "STUDY",
    src: "https://vitahani.com/assets/20190625085334.jpg",
  },
  {
    title: "제목",
    subtitle: "subtitle",
    category: "RACE_CATEGORY_VALUE",
    src:
      "https://x86.co.kr/files/attach/images/1951610/342/576/002/0555addbb9bfd672918a2539668f0dac.jpg",
  },
];
export const SERVER_BASE_URL = "https://75dd53bc223b.ngrok.io";
export const TOKEN_STORAGE = "@token_storage";

export const AnimatedImage = animated(Image);
export const AnimatedView = animated(View);
export const AnimatedText = animated(Text);
