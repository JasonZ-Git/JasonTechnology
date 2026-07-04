const express = require('express');
const {
    ActivityHandler,
    CloudAdapter,
    ConfigurationBotFrameworkAuthentication
} = require('botbuilder');

const app = express();
app.use(express.json());

const botFrameworkAuthentication = new ConfigurationBotFrameworkAuthentication(process.env);
const adapter = new CloudAdapter(botFrameworkAuthentication);

adapter.onTurnError = async function (context, error) {
    console.error(error);
    await context.sendActivity('The bot encountered an error.');
};

class EchoBot extends ActivityHandler {
    constructor() {
        super();
        this.onMessage(async function (context, next) {
            await context.sendActivity(`You said: ${context.activity.text}`);
            await next();
        });
    }
}

const bot = new EchoBot();

app.post('/api/messages', async function (req, res) {
    await adapter.process(req, res, async function (context) {
        await bot.run(context);
    });
});

const port = process.env.port || process.env.PORT || 3978;
app.listen(port, function () {
    console.log('bravura-bot listening on %s', port);
});
