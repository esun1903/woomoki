<template>
  <div>
    <span id="topBtn">TOP</span>
  </div>
</template>

<script>
import $ from 'jquery'

export default {
  name: "UpPage",
  data: function () {
    return {

    }
  },
  mounted() {

    $(window).scroll(function () {
      var height = $(document).scrollTop();
      if(height < 500) {
        $('#topBtn').hide()
      } else {
        $('#topBtn').show()
      }
    }); 
    
    var topEle = $('#topBtn');
    var delay = 500;
    topEle.on('click', function() {
      $('html, body').stop().animate({scrollTop: 0}, delay);
    });
  }
}
</script>

<style lang="scss" scoped>

#topBtn {
  position: fixed;
  right: 1.12vw;
  bottom: 5vw;
  width: 52px;
  height: 52px;
  line-height: 50px;
  display: block;
  background-color: #AED581;
  // background-position: center center;
  background-repeat: no-repeat;
  box-shadow: 12px 15px 20px 0 rgba(46, 61, 73, 0.15);
  color: #fff;
  border: 0;
  border-radius: 50%;
  outline: 0 !important;
  font-size: 15px;
  text-align: center;
  cursor: pointer;
}

</style>