# 🤖 GitHub Copilot — Get Set Up

GitHub Copilot is an AI coding assistant built into your editor. It answers questions, generates code, explains errors, and helps you build features faster — all without leaving VS Code.

## What Is GitHub Copilot?

GitHub Copilot is powered by large language models trained on code. It integrates directly into VS Code through a chat panel and inline suggestions. You describe what you want in plain English, and Copilot helps you build it — whether that's a single function or an entire feature across multiple files.

## Available Plans

| Plan | Best For | Price |
|---|---|---|
| **Free** | Individuals getting started | Free (limited monthly usage) |
| **Pro** | Individual developers who want unlimited access | ~$10/month or ~$100/year |
| **Business** | Teams and organizations | ~$19/user/month |
| **Enterprise** | Large organizations with custom policies | ~$39/user/month |

> 💡 **Tip:** The **Free** plan is a great way to try Copilot with no commitment. You get a generous monthly allowance of chat messages and code completions.

## Sign Up via github.com/copilot

This is the quickest route to get a Copilot account.

1. **Go to** [github.com/copilot](https://github.com/copilot) in your browser
2. **Sign in** with your GitHub account (or create one for free at [github.com](https://github.com))
3. **Choose a plan** — select **Free** to start at no cost, or pick a paid plan for unlimited usage
4. **Confirm your selection** — follow the on-screen prompts to activate your plan
5. **You're done** — your GitHub account now has Copilot enabled

> 📝 **Note:** If your organization provides a Business or Enterprise license, ask your admin to invite you instead of signing up individually.

## Enable Copilot in VS Code

Once your account is active, connect it to VS Code in a few steps.

1. **Open VS Code**
2. **Install the extension** — open the Extensions panel (`Ctrl+Shift+X` / `Cmd+Shift+X`), search for **GitHub Copilot**, and click **Install**
   - This also installs **GitHub Copilot Chat** automatically
3. **Sign in to GitHub** — VS Code will show a prompt in the bottom-right corner; click **Sign In to GitHub** and complete the browser login flow
4. **Authorize the extension** — click **Authorize GitHub Copilot** in the browser when asked, then return to VS Code

> ⚠️ **Warning:** Make sure you sign in with the same GitHub account that has Copilot enabled. Signing in with a different account will result in an "access not available" error.

## Verify It's Working

Run a quick check to confirm everything is connected.

1. **Open Copilot Chat** — click the chat icon in the VS Code Activity Bar (left sidebar), or press `Ctrl+Alt+I` / `Cmd+Option+I`
2. **Type a test message:**
   ```
   What is GitHub Copilot?
   ```
3. **Expect a reply** — Copilot should respond immediately in the chat panel
4. **Check inline completions** — open any code file, start typing a function, and look for a greyed-out suggestion appearing as you type; press `Tab` to accept it

If the chat panel says "Sign in to use Copilot", repeat the sign-in step above.

## 💡 Tips

- **Stay on the free tier.** The Free plan resets each month — it's plenty for learning and small projects.
- **Keep the extension updated.** Copilot improves frequently; VS Code will notify you when updates are available.
- **One account, many editors.** Your Copilot subscription also works in JetBrains IDEs, Vim, and other supported editors.
- **Check your usage.** Visit [github.com/settings/copilot](https://github.com/settings/copilot) to see your remaining free-tier quota at any time.

## What Comes Next?

With Copilot installed and running, start by learning how to ask it questions about your project. Head to [Ask Mode](01-ask-mode.md) to explore your codebase and get oriented before writing any code.
