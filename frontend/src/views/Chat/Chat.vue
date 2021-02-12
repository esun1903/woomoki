<template>
  <div class="loader">
    <!-- chatting box -->
    <noscript>
      <strong></strong>
    </noscript>
    <div id="app"></div>

    <button class="chatbox-open">
      <v-icon color="white">fas fa-comment-dots</v-icon>
    </button>
    <button class="chatbox-close">
      <i><font-awesome-icon :icon="faTimes" size="2x" aria-hidden="true" /></i>
    </button>

    <!-- <section class="chatbox-popup">
      <header class="chatbox-popup__header">
        <aside style="flex: 1">
          <v-icon color="white">fas fa-comments</v-icon>
        </aside>
        <aside style="flex: 1">
          <h1>채팅</h1>
        </aside>
        <aside style="flex: 1">
          <button class="chatbox-maximize">
            <i
              ><font-awesome-icon
                :icon="faWindowMaximize"
                size="2x"
                aria-hidden="true"
            /></i>
          </button>
        </aside>
      </header>
      <main id="chatting_box" class="chatbox-popup__main">
        <p v-if="chattings">{{ chattings }}</p>
        <p v-else>사이트에 궁금한 점이 있으시면 물어봐주세요</p>
      </main>
      <footer class="chatbox-popup__footer">
        <span class="d-flex">
          <i
            ><font-awesome-icon
              :icon="faUserCircle"
              :style="{ color: '#808080' }"
              size="2x"
              aria-hidden="true"
              class="d-flex mr-3"
          /></i>
          <aside style="flex: 10">
            <textarea
              v-model="query"
              id="text"
              type="text"
              placeholder="채팅을 입력하세요"
              autofocus
              @keypress.enter="transferQuery"
            ></textarea>
          </aside>
        </span>
        <aside style="flex: 1; color: #888; text-align: center">
          <i class="fa fa-paper-plane" aria-hidden="true"></i>
        </aside>
      </footer>
    </section> -->

    <section class="chatbox-panel loader">
      <header class="chatbox-panel__header">
        <aside style="flex: 1">
          <v-icon color="white">fas fa-comments</v-icon>
        </aside>
        <aside style="flex: 1">
          <h1>채팅</h1>
        </aside>
        <aside style="flex: 1; text-align: right">
          <button class="chatbox-panel-close">
            <v-icon color="white">fas fa-times</v-icon>
          </button>
        </aside>
      </header>
      <vuescroll>
        <main class="chatbox-panel__main" style="flex: 1">
          <div v-for="(message, idx) in Messages" :key="idx">
            <p class="text-left bot-message-tooltip" v-if="idx % 2">
              <i
                ><font-awesome-icon
                  :icon="faRobot"
                  size="1x"
                  aria-hidden="true"
                  class="chatbox-popup__avatar mr-2"
              /></i>
              <span>{{ message }}</span>
            </p>
            <p class="text-right my-message-tooltip" v-else>
              <i
                ><font-awesome-icon
                  :icon="faUserCircle"
                  size="1x"
                  aria-hidden="true"
                  class="mr-2"
              /></i>
              <span>{{ message }}</span>
            </p>
          </div>
        </main>
      </vuescroll>
      <footer class="chatbox-panel__footer">
        <i
          ><font-awesome-icon
            :icon="faUserCircle"
            :style="{ color: '#808080' }"
            size="2x"
            aria-hidden="true"
            class="d-flex mr-3"
        /></i>
        <aside style="flex: 10">
          <textarea
            v-model="query"
            type="text"
            placeholder="채팅을 입력하세요!"
            autofocus
            @keypress.enter="transferQuery"
          ></textarea>
        </aside>
        <aside style="flex: 1; color: #888; text-align: center">
          <i class="fa fa-paper-plane" aria-hidden="true"></i>
        </aside>
      </footer>
    </section>
  </div>
</template>

<script src="path/jquery-3.3.1.min.js"></script>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>

<script>
import jQuery from "jquery";
import axios from "axios";
import vuescroll from "vuescroll";

export default {
  name: "ChatBot",
  components: {
    vuescroll,
  },
  data: function () {
    return {
      query: "",
      chattings: "",
      Messages: [],
    };
  },
  methods: {
    transferQuery: function () {
      const query = {
        query: this.query,
      };

      axios
        .post("http://127.0.0.1:8000/movies/pingpong/", query)
        .then((res) => {
          this.Messages.push(this.query);
          this.chattings = res.data;
          this.Messages.push(this.chattings);
          this.query = "";
          console.log(res.data);
        });
    },
  },
};
const chatbox = jQuery.noConflict();

chatbox(() => {
  chatbox(".chatbox-open").click(() => {
    chatbox(".chatbox-popup, .chatbox-open, .chatbox-close").fadeOut();
    chatbox(".chatbox-panel").fadeIn();
    chatbox(".chatbox-panel").css({ display: "flex" });
  });

  chatbox(".chatbox-panel-close").click(() => {
    chatbox(".chatbox-panel").fadeOut();
    chatbox(".chatbox-open").fadeIn();
  });
});
</script>

<style lang="scss" scoped>
@import "./Chat.scss";

.loader {
  z-index: 0;
  position: fixed;
  justify-content: center;
}

.__bar-is-vertical {
  background: rgb(80, 80, 80) !important;
}
</style>