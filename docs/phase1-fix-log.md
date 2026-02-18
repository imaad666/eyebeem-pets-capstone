# Phase 1 Fix Log – Static Review and Manual Test Evidence

Requirements were derived from the capstone PPT; a basic frontend was implemented for all six modules, then statically analyzed. The following seven fixes were applied and verified with manual testing.

| Fix ID | Category | Description | File(s) changed | Evidence |
|--------|----------|-------------|-----------------|----------|
| 1 | Performance – font loading | Replaced Google Fonts `@import` with local bundled fonts (`@fontsource/vt323`, `@fontsource/press-start-2p`) and `font-display: swap` to avoid render-blocking and improve LCP. | `frontend/src/index.css`, `frontend/package.json` | Lighthouse Performance score ≥ 80 (screenshot in `phase1-evidence/`) |
| 2 | SEO / best practices – meta | Set meaningful `<title>` ("EYE BEE M Pets") and added `<meta name="description">` for SEO and sharing. | `frontend/index.html` | Screenshot of browser tab and/or View Page Source |
| 3 | Accessibility – semantic structure | Ensured each view has one `<main>` (Layout wraps `<Outlet />` in `<main>`), with `<header>`, `<nav>` (with `aria-label="Main navigation"`), and `<footer>`. Pages use semantic headings. | `frontend/src/components/Layout.jsx`, all page components | Screenshot of DevTools accessibility tree or Elements panel |
| 4 | Accessibility – form labels | Auth and checkout inputs have associated `<label>` with `htmlFor`/`id` and `aria-label` where appropriate. | `frontend/src/components/Layout.jsx`, `frontend/src/pages/LoginPage.jsx`, `frontend/src/pages/CheckoutPage.jsx` | Screenshot of login/checkout form with labels visible |
| 5 | Accessibility – focus | Added `:focus-visible` styles for buttons, links, and inputs so keyboard users get a clear 2px outline. | `frontend/src/index.css` | Screenshot of focused button or input (Tab to focus) |
| 6 | Maintainability – dead code | Removed unused components that duplicated or conflicted with the retro design: `Navbar.jsx`, `Hero.jsx`, `Footer.jsx`, `ProductCard.jsx`, and the monolithic `LandingPage.jsx`. Replaced with shared Layout (Header/Footer), context, and dedicated page components. | Deleted files; new `Layout.jsx`, `AppContext.jsx`, pages | Screenshot of `frontend/src` file tree or linter with no unused exports |
| 7 | Correctness – dialogs and links | Overlay modals use `role="dialog"`, `aria-modal="true"`, and `aria-labelledby` for screen readers. Replaced invalid button-inside-Link with button + `navigate()` on Cart page. | `frontend/src/components/Layout.jsx`, `frontend/src/pages/CartPage.jsx` | Screenshot of overlay and/or cart → checkout flow |

## Narrative for capstone

We derived requirements from the PPT (six modules: registration/login, product search, cart, order placement, payment, order history). We implemented a basic frontend covering these modules with a single-page flow and overlays, then statically analyzed the code and applied the seven improvements above. Each fix was verified with manual tests; screenshots are stored in `docs/phase1-evidence/`.

## How to capture evidence

1. **Fix 1**: Run Lighthouse (Chrome DevTools → Lighthouse), Performance category; ensure score ≥ 80. Save screenshot.
2. **Fix 2**: Open the app, screenshot the browser tab showing title; or View Page Source and screenshot the `<head>`.
3. **Fix 3**: In DevTools, use Accessibility panel or Elements to show `<main>`, `<header>`, `<nav>`, `<footer>` structure; screenshot.
4. **Fix 4**: Open `/login` and `/checkout`, screenshot forms showing labels.
5. **Fix 5**: Tab through the page until a button or input is focused; screenshot the focus ring.
6. **Fix 6**: Screenshot the `frontend/src` directory listing (or run `npm run lint` and screenshot clean result).
7. **Fix 7**: Open cart overlay and/or complete cart → checkout; screenshot.

Save screenshots in `docs/phase1-evidence/` with names like `01-lighthouse.png`, `02-meta.png`, etc.
