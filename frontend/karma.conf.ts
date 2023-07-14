module.exports = (config: any) => {
  config.set({
    customLaunchers: {
      ChromeHeadless: {
        base: 'Chrome',
        flags: [
          '--no-sandbox',
          '--disable-gpu',
          '--headless',
          '--remote-debugging-port=9222'
        ]
      }
    },
  });
}
