<script lang="ts">
  import { goto } from '$app/navigation';
  import type { <%= entityNmPascal %>Model } from '$lib/arch/api/Api';
  import FormValidator from '$lib/arch/form/FormValidator';
  import CriteriaUtils from '$lib/arch/search/CriteriaUtils';
  import ListTable, { ColumnsBuilder } from '$lib/arch/search/ListTable.svelte';
  import type { PageData } from '../<%= entityNmPlural %>/$types';
  import { t } from '$lib/translations';

  let { data }: { data: PageData } = $props();
  let { criteria } = $state(data);
  let { result } = $derived(data);

  const form = FormValidator.createForm({}, search);

  const columns = new ColumnsBuilder<<%= entityNmPascal %>Model>().add('#', 'i.id', () => <%= entityNmCamel %>IdAnchor).build();

  function search() {
    goto(CriteriaUtils.encode(criteria));
  }
</script>

<section>
  <form use:form>
    <fieldset role="search">
      <input type="search" bind:value={criteria.text} />
      <input type="submit" value="Search" />
    </fieldset>
  </form>
</section>

<section>
  <a id="new<%= entityNmPascal %>" href="/<%= entityNmPlural %>/new"> {$t('msg.newEntity')} </a>
</section>

<section>
  <ListTable {result} {columns} bind:pageControl={criteria.pageControl} {search} />
</section>

<%_
idPath = compIdFields ? compIdFields.map((field) => `\${${entityNmCamel}.id.${field.fieldName}}`).join("/") : `\${${entityNmCamel}.id}`;
-%>
{#snippet <%= entityNmCamel %>IdAnchor(<%= entityNmCamel %>: <%= entityNmPascal %>Model)}
  <a href={`/<%= entityNmPlural %>/<%= idPath %>`}>{`<%= idPath %>`}</a>
{/snippet}
