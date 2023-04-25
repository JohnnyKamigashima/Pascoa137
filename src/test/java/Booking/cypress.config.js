module.exports = {
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },

    // BaseURL e uma boa pratica
    baseUrl: "https://restful-booker.herokuapp.com",
    retries: { //tenta quando falha mais vezes
      runMode: 1, // no modo cli
      openMode: 4, // no modo debug
    },
    viewportHeight: 1080, //Resulucao de tela e uma boa pratica
    viewportWidth: 1920,
  },
};
