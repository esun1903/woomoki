<template>
  <div>
    <!-- chatting box -->
    <noscript>
      <strong></strong>
    </noscript>
    <div id="app"></div>

    <button @click="connect" class="chatbox-open">
      <v-icon color="white">fas fa-comment-dots</v-icon>
    </button>
    <button class="chatbox-close">
       <v-icon color="white">fas fa-times</v-icon>
    </button>

    <section class="chatbox-panel loader">
      <header class="chatbox-panel__header">
        <aside style="flex: 1">
          <v-icon color="white">fas fa-comments</v-icon>
        </aside>
        <aside style="flex: 1">
          <h1>채팅</h1>
        </aside>
        <aside style="flex: 1; text-align: right">
          <button @click="disconnect" class="chatbox-panel-close">
            <v-icon color="white">fas fa-times</v-icon>
          </button>
        </aside>
      </header>
      <vuescroll :ops="ops">
        <main class="chatbox-panel__main" style="flex: 1">
          <div v-for="(log, idx) in logs" :key="idx" class="message-box">
            <div class="reverse">
              <p class="enter">
                <span v-if="idx === 0">
                  {{ log.event }}
                </span>
              </p>
              <p v-if="log.event === '전송'" class="text-left">
                <span class="send-message-tooltip">
                  {{ log.event }}: {{ log.data }}
                </span>
              </p>
              <p v-if="log.event === '수신'" class="text-left">
                <span class="recieve-message-tooltip">
                  {{ log.event }}: {{ log.data }}
                </span>
              </p>
            </div>
          </div>
        </main>
      </vuescroll>
      <footer class="chatbox-panel__footer">
        <v-icon>fas fa-user-circle</v-icon>
        <aside style="flex: 10">
          <textarea
            class="d-flex align-center"
            v-model="message"
            type="text"
            placeholder="채팅을 입력하세요!"
            autofocus
            @keypress.enter="sendMessage"
          ></textarea>
        </aside>
        <aside style="flex: 1; color: #888; text-align: center">
          <v-btn icon>
            <v-icon @click="sendMessage">fas fa-paper-plane</v-icon>
          </v-btn>
        </aside>
      </footer>
    </section>
  </div>
</template>

<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>

<script>
import jQuery from "jquery";
import vuescroll from "vuescroll";

export default {
  name: "Chat",
  components: {
    vuescroll,
  },
  data: function () {
    return {
      message: "",
      logs: [],
      status: "disconnected",
      ops: {
          bar: {
             background: '#AED581',
          }
        }
    };
  },
  methods: {
    connect() {
      this.socket = new WebSocket("wss://echo.websocket.org");
      this.socket.onopen = () => {
      this.status = "connected";
      // this.logs.push({ event: "채팅방에 입장했습니다", data: 'wss://echo.websocket.org'})
      this.logs.push({ event: "채팅방에 입장했습니다"})
      this.socket.onmessage = ({data}) => {
      this.logs.push({ event: "수신", data });
      };
      };
    },
    disconnect() {
      this.socket.close();
      this.status = "disconnected";
      this.logs = [];
    },
    sendMessage(e) {
      this.socket.send(this.message);
      this.logs.push({ event: "전송", data: this.message });
      this.message = "";
    }
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

.enter {
  background-color: white;
}


</style>
